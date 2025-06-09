package com.matrix.buildingapp.service.impl;

import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.mapper.CommentMapper;
import com.matrix.buildingapp.model.dto.requestDto.CommentRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.CommentResponseDto;
import com.matrix.buildingapp.model.entity.Comment;
import com.matrix.buildingapp.model.entity.ConstructionCompany;
import com.matrix.buildingapp.repository.CommentRepository;
import com.matrix.buildingapp.repository.ConstructionCompanyRepository;
import com.matrix.buildingapp.service.CommentService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper ;
    private final CommentRepository commentRepository;
    private  final ConstructionCompanyRepository constructionCompanyRepository;

    @Override
    public CommentResponseDto add(CommentRequestDto commentRequestDto) {
        Comment comment=commentMapper.mapToEntity(commentRequestDto);
        ConstructionCompany constructionCompany=constructionCompanyRepository.findById(commentRequestDto.getConstructionCompanyId()).orElseThrow(()->new NotFoundException("Construction company not found"));
        comment.setConstructionCompany(constructionCompany);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        CommentResponseDto commentResponseDto=commentMapper.mapToResponse(comment);
        commentResponseDto.setConstructionCompanyId(constructionCompany.getId());
        return commentResponseDto;
    }

    @Override
    public CommentResponseDto getById(Integer id) {
        Comment comment= commentRepository.findById(id).orElseThrow(()-> new NotFoundException("Comment not found"));
        return commentMapper.mapToResponse(comment);

    }

    @Override
    public CommentResponseDto update(Integer id,CommentRequestDto commentRequestDto) {
        Comment comment=commentRepository.findById(id).orElseThrow(()->new NotFoundException("comment not found"));
        Comment comment1 =commentMapper.map(commentRequestDto,comment);
        commentRepository.save(comment1);
        return commentMapper.mapToResponse(comment1);

    }

    @Override
    public void delete(Integer id) {
    commentRepository.findById(id).orElseThrow(()-> new NotFoundException("comment not found"));
    commentRepository.deleteById(id);
    }

    @Override
    public List<CommentResponseDto> getAll() {
        List<Comment> comments= commentRepository.findAll();
        return comments.stream()
                .map(commentMapper :: mapToResponse)
                .collect(Collectors.toList());
    }
}
