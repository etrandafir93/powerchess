package ro.etr.minicourse.helper;

import static java.util.Arrays.stream;
import static ro.etr.minicourse.entity.board.Square.square;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class SquareArrayConverter implements ArgumentConverter {
    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        if (source instanceof String squares) {
            return stream((squares).split(",")).map(s -> square(s)).toList();
        }
        throw new IllegalArgumentException("The argument should be a string: " + source);
    }
}