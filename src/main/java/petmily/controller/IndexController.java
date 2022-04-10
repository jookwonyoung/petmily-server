package petmily.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import petmily.config.auth.LoginUser;
import petmily.config.auth.dto.SessionUser;
import petmily.service.post.PostService;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        model.addAttribute("post", postsService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
            //model.addAttribute("email", user.getEmail());
        }

        return "index";
    }

    @GetMapping("/post/save")
    public String postsSave() {
        return "posts-save";
    }

//    @GetMapping("/posts/update/{id}")
//    public String postsUpdate(@PathVariable Long id, Model model) {
//        PostsResponseDto dto = postsService.findById(id);
//        model.addAttribute("post", dto);
//
//        return "posts-update";
//    }
}
