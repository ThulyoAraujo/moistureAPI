package com.tulio.reader.exception

import com.tulio.reader.model.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerError(exception: Exception, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(exception: NotFoundException, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException, request: HttpServletRequest): ErrorView {
        //O HashMap abaixo lê-se HashMap<campoDoErro, mensagemDoErro>
        val errorMessage = HashMap<String, String?>()
        /** Abaixo pega-se os campos de erro no resultado das exceções.
         *  Para cada erro, é captado o nome do campo e a mensagem padrão.
         */
        exception.bindingResult.fieldErrors.forEach{
            error -> errorMessage.put(error.field, error.defaultMessage)
        }
        /* Da seguinte forma é possível mostrar todos os erros que aconteram ao mesmo tempo:
                exception.bindingResult.allErrors.map{error -> error.defaultMessage} */
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = errorMessage.toString(),
            path = request.servletPath
        )
    }
}