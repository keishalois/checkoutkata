package kata.Controller;

import kata.Repository.ItemRepo;
import kata.Model.Sku;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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

    @PostMapping()
    public ResponseEntity addNewSku(@RequestBody Sku newSku) throws URISyntaxException {
        if(itemRepo.getSku(newSku.getNameOfProduct()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("SKU already exists", newSku.getNameOfProduct()));
        }

        itemRepo.addSku(newSku);
        return ResponseEntity.created(new URI("skus/" + newSku.getNameOfProduct())).body(newSku);
    }
}








