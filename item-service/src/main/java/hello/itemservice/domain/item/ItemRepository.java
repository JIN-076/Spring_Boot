package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    /**
     * 멀티 쓰레드 환경에서 다중 사용자가 동시에 접근해야 하는 경우, HashMap<>()을 사용해서는 안된다.
     * 싱글톤으로 생성이 되기 때문.
     * 또한, sequence 역시 long 사용 대신, AtomicLong 사용해야 한다.
     * private static final Map<Long, Item> store = new ConcurrentHashMap<>();
     * private static AtomicLong sequence = 0L;
     */

    private static final Map<Long, Item> store = new HashMap<>(); // static
    private static long sequence = 0L; // static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * 실무에서 다음과 같이 사용하고자 할 경우,
     * ItemParamDTO 객체 생성 후, 필요한 파라미터만을 넣어놓는 것이 맞다.
     * 변경되어서는 안 될 Id 요소에 대한 setter 공개되어 있기 때문.
     * @param updateParam
     */

    public void update(Long ItemId, Item updateParam) {
        Item findItem = findById(ItemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
