package ir.mojir.spring_boot_commons.helpers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import ir.mojir.spring_boot_commons.dtos.Paging;
import ir.mojir.spring_boot_commons.dtos.Sorting;

public class RepositoryHelper {
	public static PageRequest generatePageRequest(Paging paging)
	{
		return PageRequest.of(paging.getPageNumber(), paging.getPageSize());
	}
	
	public static PageRequest generatePageRequestWithSort(Paging paging, Sorting sorting)
	{
		if(paging == null)
			paging = new Paging();
		
		if(sorting == null)
			return generatePageRequest(paging);
		
		if(sorting.getSortField() == null || sorting.getSortField().equals(""))
			return generatePageRequest(paging);
		
		Sort sort = Sort.by(sorting.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC, sorting.getSortField());
		return PageRequest.of(paging.getPageNumber(), paging.getPageSize(), sort);
	}
}
