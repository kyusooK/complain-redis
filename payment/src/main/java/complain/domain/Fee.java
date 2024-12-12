package complain.domain;

import complain.PaymentApplication;
import complain.domain.결재완료됨;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Fee_table")
@Data
//<<< DDD / Aggregate Root
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String applicationNumber;

    private Long charge;

    private String userId;

    @PostPersist
    public void onPostPersist() {
        결재완료됨 결재완료됨 = new 결재완료됨(this);
        결재완료됨.publishAfterCommit();
    }

    public static FeeRepository repository() {
        FeeRepository feeRepository = PaymentApplication.applicationContext.getBean(
            FeeRepository.class
        );
        return feeRepository;
    }

    //<<< Clean Arch / Port Method
    public void pay(PayCommand payCommand) {
        //implement business logic here:

        complain.external.GetInfoQuery getInfoQuery = new complain.external.GetInfoQuery();
        // getInfoQuery.set??()
        User user = FeeApplication.applicationContext
            .getBean(complain.external.UserService.class)
            .userInfo(getInfoQuery);
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
