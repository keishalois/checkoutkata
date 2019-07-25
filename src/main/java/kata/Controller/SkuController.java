package kata.Controller;

import kata.repo.ItemRepo;
import kata.sku.Sku;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sku")
public class SkuController {

    private ItemRepo itemRepo;

    public SkuController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/{skuid}")
    public ResponseEntity getSku(@PathVariable String skuid) {
        if (itemRepo.getSku(skuid) != null) {
            return ResponseEntity.ok(itemRepo.getSku(skuid));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("SKU %s does not exist", skuid));
        }
    }

    @PostMapping("/{skuid}")
    public void newsku(@RequestBody String name, @RequestBody Sku newSku) {
        itemRepo.getAllSkus().put(name, newSku);
    }

    }






