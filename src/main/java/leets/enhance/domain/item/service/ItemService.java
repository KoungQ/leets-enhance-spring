package leets.enhance.domain.item.service;

import jakarta.transaction.Transactional;
import leets.enhance.domain.item.entity.DestroyProbability;
import leets.enhance.domain.item.entity.Item;
import leets.enhance.domain.item.entity.Status;
import leets.enhance.domain.item.entity.SuccessProbability;
import leets.enhance.domain.item.exception.InvalidAccessException;
import leets.enhance.domain.item.model.request.ItemRegisterDto;
import leets.enhance.domain.item.repository.ItemRepository;
import leets.enhance.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static leets.enhance.global.error.ErrorCode.INVALID_ITEM;
import static leets.enhance.global.error.ErrorCode.ITEM_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item register(User user, ItemRegisterDto requestDto) {
        if(user.getItem() != null)
            throw new InvalidAccessException(ITEM_ALREADY_EXIST);   // 이미 아이템이 존재하면 ERROR

        Item item = Item.of()
                .name(requestDto.getName())
                .user(user)
                .build();

        user.registerItem(item);    // User - Item 연관관계 매핑

        return itemRepository.save(item);
    }

    public List<Item> findTop10() {
        return itemRepository.findAll().stream()
                .sorted(Comparator.comparing(Item::getLevel).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    @Transactional
    public Status enhance(Item item, boolean isBoosted) {
        double successProb = SuccessProbability.getProbability(item.getLevel());

        User user = item.getUser();
        if(isBoosted && user.getBooster() > 0) {     // 강화 증가권 사용 시 성공 확률 10퍼센트 증가
            user.useBooster();
            successProb += 10;
        }

        if(Math.random() <= successProb) {   // 강화 성공
            return item.success();
        } else {    // 실패 시
            if(Math.random() <= DestroyProbability.getProbability(item.getLevel())) // 파괴 성공
                return item.destroy();
            return item.fail(); // 파괴 실패
        }
    }

    public Item getItem(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new InvalidAccessException(INVALID_ITEM));
    }
}
