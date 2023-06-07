package com.tulio.reader.model.form

import javax.validation.constraints.NotNull

class ReaderForm (
    @field:NotNull
    var moisture: Int? = null,
    var date: String? = null,
    var time: String? = null,
    var latitude: String? = null,
    var longitude: String? = null
)