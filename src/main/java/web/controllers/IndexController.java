package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.json.AjaxResponse;
import utils.json.AjaxResponseStatus;
import web.controllers.utils.TokenUtils;
import web.controllers.utils.transfer.TokenTransfer;

import java.util.Map;

/**
 * Created by vlasov-id-131216 on 08.03.15.
 */
@Controller
@RequestMapping(value="/", produces="application/json; charset=utf-8")
public class IndexController {

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    @Autowired
    @Qualifier("userService")
    private UserDetailsService userService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getForm(Map<String, Object> map) {
        return "index";
    }

    @RequestMapping(value="/authenticate", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResponse authenticate(@RequestParam("username") String username,
                                     @RequestParam("password") String password) {
        AjaxResponse response = new AjaxResponse();
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userService.loadUserByUsername(username);
            response.setStatus(AjaxResponseStatus.OK);
            response.setData(userDetails);
            response.setMessage(new TokenTransfer(TokenUtils.createToken(userDetails)).getToken());
            return response;
        } catch (Throwable t) {
            response.setStatus(AjaxResponseStatus.ERROR);
            response.setMessage(t.getMessage());
            return response;
        }
    }
}
