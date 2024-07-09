package ding_a_ling_a_ling.dating_webapp.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping(value = { "/", "/login", "/register", "/match/*", "/profile/**" })
  public String entry() {
    return "index";
  }

}
