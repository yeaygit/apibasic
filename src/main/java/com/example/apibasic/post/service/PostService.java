package com.example.apibasic.post.service;

import com.example.apibasic.post.dto.*;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
//스프링빈 등록
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    //삽입


    //목록 조회 중간처리
    public PostListResponseDTO getList() { //throws RuntimeException 에서 throws는 던지다 -> 오류를 한 번에 모아서 처리
        final List<PostEntity> list = postRepository.findAll();

        if(list.isEmpty()){
            throw new RuntimeException("조회 결과가 없어요"); //throw 유발하다
        }

        // 엔터티 리스트를 DTO리스트로 변환해서 클라이언트에 응답
        List<PostResponseDTO> responseDTOList = list.stream()
                .map(PostResponseDTO::new)
                .collect(toList());

        PostListResponseDTO listResponseDTO = PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .posts(responseDTOList)
                .build();

        return listResponseDTO;
    }

    //개별 조회 중간 처리
    public PostDetailResponseDTO getDetail(Long postNo){
        PostEntity post = postRepository.findById(postNo)
                .orElseThrow(()->
                        new RuntimeException(
                                postNo+"번 게시물이 존재하지 않음"
                        ))
                ;


        //PostEntity postEntity =post.orElseThrow(); //null 체크+ throw도 함께
        //if (post==null) throw new RuntimeException(postNo+"번 게시물이 존재하지 않음");


        return new PostDetailResponseDTO(post);
    }

    //등록 중간처리
    public PostDetailResponseDTO insert(final PostCreateDTO createDTO)
        throws RuntimeException{
        final PostEntity entity = createDTO.toEntity();
        PostEntity savedPost = postRepository.save(entity); //save하다가 에러발생할 수 있으므로 에러 처리 필요
        //저장된 객체를 dto로 변환해서 반환

        return new PostDetailResponseDTO(savedPost); //보여줄것만 보여주기 위해서 dto로 다시 변환해서 반환
    }

    //수정 중간 처리
    public PostDetailResponseDTO update(final Long postNo, final PostModifyDTO modifyDTO)
        throws RuntimeException{
        // 수정 전 데이터 조회하기
        final PostEntity entity = postRepository
                .findById(postNo)
                .orElseThrow(()->new RuntimeException("수정 전 데이터가 존재하지 않습니다."));
        // 수정 진행
        String modTitle = modifyDTO.getTitle();
        String modContent = modifyDTO.getContent();

        if (modTitle != null) entity.setTitle(modTitle);
        if (modContent != null) entity.setContent(modContent);
        //entity.setModifyDate(LocalDateTime.now());

        PostEntity modifiedPost=postRepository.save(entity);

        return new PostDetailResponseDTO(modifiedPost);
    }

    //삭제 중간처리
    public void delete(final Long postNo)
        throws RuntimeException{
        //postRepository.delete(postRepository.findById(postNo).get());
        postRepository.deleteById(postNo);
    }
}
