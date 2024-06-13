package br.com.itau.pix.repository.impl;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.repository.PixKeyRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
        }

        long total = mongoTemplate.count(query, PixKeyDTO.class);
        query.with(pageable);

        List<PixKeyDTO> pixKeyDTOS = mongoTemplate.find(query, PixKeyDTO.class);
        return new PageImpl<>(pixKeyDTOS, pageable, total);
    }
}