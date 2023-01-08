package ro.etr.minicourse.helper;

import static ro.etr.minicourse.entity.board.Square.square;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class SquareConverter implements ArgumentConverter {
    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        if (source instanceof String squareStr) {
            return square(squareStr);
        }
        throw new IllegalArgumentException("The argument should be a string: " + source);
    }
}