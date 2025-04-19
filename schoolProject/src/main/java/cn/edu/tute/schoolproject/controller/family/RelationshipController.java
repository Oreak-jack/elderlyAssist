package cn.edu.tute.schoolproject.controller.family;

import cn.edu.tute.schoolproject.entity.Relationship;
import cn.edu.tute.schoolproject.service.family.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/family/relationship")
public class RelationshipController {

    @Autowired
    private RelationshipService relationshipService;

    @PostMapping
    public ResponseEntity<Integer> addRelationship(@RequestBody Relationship relationship) {
        int result = relationshipService.addRelationship(relationship);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteRelationship(@PathVariable Integer id) {
        int result = relationshipService.deleteRelationship(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<Integer> updateRelationship(@RequestBody Relationship relationship) {
        int result = relationshipService.updateRelationship(relationship);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relationship> getRelationshipById(@PathVariable Integer id) {
        Relationship relationship = relationshipService.getRelationshipById(id);
        return ResponseEntity.ok(relationship);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Relationship>> getRelationshipsByUserId(@PathVariable Integer userId) {
        List<Relationship> relationships = relationshipService.getRelationshipsByUserId(userId);
        return ResponseEntity.ok(relationships);
    }

    @GetMapping("/reminder/{reminderId}")
    public ResponseEntity<List<Relationship>> getRelationshipsByReminderId(@PathVariable Integer reminderId) {
        List<Relationship> relationships = relationshipService.getRelationshipsByReminderId(reminderId);
        return ResponseEntity.ok(relationships);
    }

    @GetMapping
    public ResponseEntity<List<Relationship>> getAllRelationships() {
        List<Relationship> relationships = relationshipService.getAllRelationships();
        return ResponseEntity.ok(relationships);
    }
}