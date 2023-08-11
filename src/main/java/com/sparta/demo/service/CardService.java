package com.sparta.demo.service;

import com.sparta.demo.dto.response.CardResponseDto;
import com.sparta.demo.dto.reuqest.CardRequestDto;
import com.sparta.demo.entity.Card;
import com.sparta.demo.entity.Section;
import com.sparta.demo.entity.User;
import com.sparta.demo.repository.CardRepository;
import com.sparta.demo.repository.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final SectionRepository sectionRepository;

    public CardService(CardRepository cardRepository, SectionRepository sectionRepository) {
        this.cardRepository = cardRepository;
        this.sectionRepository = sectionRepository;
    }

    // 카드 생성
    public CardResponseDto createCard(CardRequestDto requestDto, User user) {
        //해당되는 section entity를 가져오기
        Section section = sectionRepository.findById(requestDto.getSectionId()).orElseThrow(
                () -> new IllegalArgumentException("해당되는 column을 찾을수 없습니다.")
        );

        //card entity를 만들어서 저장
        Card card = new Card(requestDto, user, section);
        cardRepository.save(card);

        //dto로 만들어서 반환해줌
        return new CardResponseDto(card);
    }


    // 카드 정보 조회( Test용도 )
    public List<CardResponseDto> getCards() {
        List<Card> cardList = cardRepository.findAll();
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();

        for (int i = 0; i < cardList.size(); i++) {
            cardResponseDtoList.add(new CardResponseDto(cardList.get(i)));
        }

        return cardResponseDtoList;
    }

    // 카드 하나 조회
    public CardResponseDto getOneCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("카드가 존재하지 않습니다.")
        );

        return new CardResponseDto(card);
    }

    // 카드 수정
    @Transactional
    public CardResponseDto updateCard(Long id, CardRequestDto requestDto, User user) {

        //해당되는 entity찾기
        Card card = findById(id);

        card.update(requestDto);

        return new CardResponseDto(card);
    }

    // 카드 삭제
    public void deleteCard(Long id) {
        //해당되는 entity찾기
        Card card = findById(id);

        //삭제 요청
        cardRepository.delete(card);
    }

    /*----------private method-------------*/
    private Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당되는 card를 찾을수 없습니다.")
        );
    }
}
