package complain.infra;

import complain.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/users")
@Transactional
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/users/userInfo")
    public User userInfo(GetInfoQuery getInfoQuery) {
        return userRepository.userInfo(getInfoQuery.getId());
    }
}
//>>> Clean Arch / Inbound Adaptor
