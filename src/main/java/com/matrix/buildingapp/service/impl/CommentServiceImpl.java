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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Data
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper ;
    private final CommentRepository commentRepository;
    private  final ConstructionCompanyRepository constructionCompanyRepository;

    @Override
    public CommentResponseDto add(CommentRequestDto commentRequestDto) {
        Comment comment=commentMapper.toRequestDtoMapEntity(commentRequestDto);
        ConstructionCompany constructionCompany=constructionCompanyRepository.findById(commentRequestDto.getConstructionCompanyId()).orElseThrow(()->new NotFoundException("Construction company not found"));
        comment.setConstructionCompany(constructionCompany);
        commentRepository.save(comment);
        CommentResponseDto commentResponseDto=commentMapper.toEntityMapResponseDto(comment);
        return commentResponseDto;
    }

    @Override
    public CommentResponseDto getById(Integer id) {
        Comment comment= commentRepository.findById(id).orElseThrow(NullPointerException::new);
        CommentResponseDto commentResponseDto =commentMapper.toEntityMapResponseDto(comment);
        return commentResponseDto;
    }

    @Override
    public CommentResponseDto update(CommentRequestDto commentRequestDto) {
        Comment comment=commentMapper.toRequestDtoMapEntity(commentRequestDto);
        commentRepository.save(comment);
        CommentResponseDto commentResponseDto=commentMapper.toEntityMapResponseDto(comment);
        return commentResponseDto;
    }

    @Override
    public void delete(Integer id) {
    commentRepository.deleteById(id);
    }

    @Override
    public List<CommentResponseDto> getAll() {
        List<Comment> comments= commentRepository.findAll();
        List<CommentResponseDto> commentResponseDto =commentMapper.map(comments);
        return commentResponseDto;
    }
}
