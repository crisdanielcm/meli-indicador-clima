package com.meli.climasistemasolar.web.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.meli.climasistemasolar.web.app.models.entity.CondicionClimatica;

@Repository
public interface ICondicionClimaticaDao extends PagingAndSortingRepository<CondicionClimatica, Long>{

}
