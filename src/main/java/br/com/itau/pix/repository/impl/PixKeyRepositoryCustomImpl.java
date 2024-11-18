package br.com.itau.pix.repository.impl;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.exception.ResourceNotFoundException;
import br.com.itau.pix.repository.PixKeyRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PixKeyRepositoryCustomImpl implements PixKeyRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Override
    public Page<PixKeyDTO> findByCustomCriteria(Map<String, Object> criteria, Pageable pageable) {

        Query query = new Query();

        if (criteria.containsKey("sortField") && criteria.containsKey("sortDirection")) {
            String sortField = (String) criteria.get("sortField");
            String sortDirection = (String) criteria.get("sortDirection");

            Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
            query.with(Sort.by(direction, sortField));
            criteria.remove("sortField");
            criteria.remove("sortDirection");
        }

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            if(criteria.containsKey("timestampInclusion") || criteria.containsKey("timestampExclusion")) {
                query.addCriteria(Criteria.where(entry.getKey()).regex(".*" + entry.getValue() + ".*"));
            }else{
                query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            }
        }

        long total = mongoTemplate.count(query, PixKeyDTO.class);
        query.with(pageable);

        List<PixKeyDTO> pixKeyDTOS = mongoTemplate.find(query, PixKeyDTO.class);

        if (pixKeyDTOS.isEmpty()) {
            throw new ResourceNotFoundException("Invalid ID", new FieldErrorDTO("Query", "No PIX Key was found with the provided query."));
        }

        return new PageImpl<>(pixKeyDTOS, pageable, total);
    }
}