package com.tulio.reader.repository

import com.tulio.reader.model.entity.Reader
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface IReaderRepository : PagingAndSortingRepository<Reader, Long>