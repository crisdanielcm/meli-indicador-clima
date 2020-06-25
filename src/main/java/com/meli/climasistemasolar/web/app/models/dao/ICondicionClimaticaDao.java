package com.meli.climasistemasolar.web.app.models.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.meli.climasistemasolar.web.app.models.entity.CondicionClimatica;

@Repository
public interface ICondicionClimaticaDao extends PagingAndSortingRepository<CondicionClimatica, Long> {

	public int countByClima(String clima);

	public List<CondicionClimatica> findByClimaOrderByPerimetroDesc(String clima);

	public CondicionClimatica findByDia(int dia);
}
