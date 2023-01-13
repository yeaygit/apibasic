package com.example.apibasic.post.api;

import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//리소스 : 게시물(Post)

/***
 *  게시물 목록 조회: /posts -GET
 *  게시물 개별 조회 : /posts/{id} -GET
 *  게시물 등록 조회: /posts -POST
 *  게시물 수정 : /posts/{id} -PATCH
 *  게시물 삭제 : /posts/{id} -DELETE
 */
@RestController
@Slf4j
@RequiredArgsConstructor //생성자 자동생성(자동 주입)
@RequestMapping("/posts")
public class PostApiController {

    //postrepository에게 의존하는 관계
    private final PostRepository postRepository;

    //@Autowired //스프링 컨테이너에게 의존 객체를 자동 주입해달라
//    public PostApiController(PostRepository postRepository){
//        this.postRepository=postRepository; //불변성 유지가 필요하기 때문에 생성자
//    }


//    public void setPostRepository(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
    //setter주입은 쓰면 안됨 -> 내가 원할 때 반복호출 가능


    //게시물 목록 조회
    @GetMapping
    public ResponseEntity<?> list(){
        log.info("/posts GET request");
        List<PostEntity> list = postRepository.findAll();
        return null;
    }

    //게시물 개별조회
    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable("postNo") Long postNo){ //@PathVariable Long postNo 가능
        log.info("/posts/{} GET request",postNo);
        return null;
    }

    //게시물 등록
    @PostMapping
    public ResponseEntity<?> create(){
        log.info("/posts POST request");
        return null;
    }

    //게시물 수정
    @PatchMapping("/{postNo}")
    public ResponseEntity<?> modify(@PathVariable Long postNo){
        log.info("/posts/{} PATCH request",postNo);
        return null;
    }

    //게시물 삭제
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> remove(@PathVariable Long postNo){
        log.info("/posts/{} Delete request",postNo);
        return null;
    }

}
