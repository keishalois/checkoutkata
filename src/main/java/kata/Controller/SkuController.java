package kata.Controller;

import kata.Model.repo.ItemRepo;
import kata.Model.sku.Sku;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skus")
public class SkuController {

    private ItemRepo itemRepo;

    public SkuController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/{skuId}")
    public ResponseEntity getSku(@PathVariable String skuId) {
        if (itemRepo.getSku(skuId) != null) {
            return ResponseEntity.ok(itemRepo.getSku(skuId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("SKU %s does not exist", skuId));
        }
    }

    @PostMapping("/post")
    public Sku addNewSku(@RequestBody Sku newSku) {
        itemRepo.addSku(newSku);
        return newSku;
    }
}








