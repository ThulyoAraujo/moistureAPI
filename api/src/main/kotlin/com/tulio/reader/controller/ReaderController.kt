package com.tulio.reader.controller

import com.tulio.reader.model.form.ReaderForm
import com.tulio.reader.service.IReaderService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/reader/")
class ReaderController(
    private val ReaderService: IReaderService
) {

    @PostMapping("create")
    fun createReader(
        @RequestBody @Valid readerForm: ReaderForm
    ): Any {
        return ReaderService.createReader(readerForm)
    }
    
    @GetMapping("list-filters")
    fun findAllReadersWithFilters(
        @RequestParam page: Int,
        @RequestParam size: Int
    ): Any {
        return ReaderService.findAllWithFilters(page, size)
    }
}