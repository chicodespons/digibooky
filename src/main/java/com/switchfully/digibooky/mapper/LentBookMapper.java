package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.LentBookDto;
import com.switchfully.digibooky.dto.LentBookOverdueDto;
import com.switchfully.digibooky.models.LentBook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LentBookMapper {

    public LentBookDto lentBookToDTO(LentBook lentBook){
        return new LentBookDto(lentBook.getBook(),lentBook.getLendingID(),lentBook.getDueDate(), lentBook.getUser().getEmail());
    }

    public List<LentBookDto> lentBookListToDTO(List<LentBook> lentBookList){
        return lentBookList.stream()
                .map(this::lentBookToDTO)
                .toList();
    }

    public LentBookOverdueDto lentBookToOverDueDTO(LentBook lentBook){
        return new LentBookOverdueDto(lentBook.getBook(),lentBook.getLendingID(),lentBook.getDueDate(),lentBook.getUser().getEmail());
    }

    public List<LentBookOverdueDto> lentBookOverdueDtoList(List<LentBook> lentBookList){
        return lentBookList.stream()
                .map(this::lentBookToOverDueDTO)
                .toList();
    }
}
