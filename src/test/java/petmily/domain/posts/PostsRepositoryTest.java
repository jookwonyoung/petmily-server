//package petmily.domain.posts;
//
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PostsRepositoryTest {
//
//    @Autowired
//    PostRepository postsRepository;
//
//    @After  //단위테스트 끝날 때마다 수행
//    public void cleanup() {
//        postsRepository.deleteAll();
//    }
//
//    @Test
//    public void 게시글저장_불러오기() {
//        //given
//        String title = "테스트 게시글";
//        String content = "테스트 본문";
//
//        postsRepository.save(Posts.builder()    //테이블 posts 에 insert or update 쿼리 실행
//                .title(title)
//                .content(content)
//                .author("jooky0327@naver.com")
//                .build());
//
//        //when
//        List<Posts> postsList = postsRepository.findAll();
//
//        //then
//        Posts posts = postsList.get(0);
//        assertThat(posts.getTitle()).isEqualTo(title);
//        assertThat(posts.getContent()).isEqualTo(content);
//    }
//}
