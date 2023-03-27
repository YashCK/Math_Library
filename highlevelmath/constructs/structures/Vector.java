package highlevelmath.constructs.structures;

import highlevelmath.constructs.abstract_algebra.alg_structures.Field;
import highlevelmath.constructs.util.NotInvertibleException;
import highlevelmath.constructs.util.OperationUndefinedException;

import java.util.Arrays;

public class Vector<E extends Field<E>> extends Vec<E, E>{

    public Vector(E... values) {
        super(values);
    }

    @Override
    public void scale(E factor) {
        for (E datum : data) {
            datum.multiply(factor);
        }
    }

    @Override
    public E dot(Vec<E, E> vector) {
        if (data.length != vector.length()) {
            throw new RuntimeException(OPER_DIFFERING_LENGTHS);
        }
        E sum = data[0].getZero();
        for (int i = 0; i < data.length; i++) {
            E copy = data[i].copy();
            copy.multiply(vector.get(i));
            sum.add(copy);
        }
        return sum;
    }

    @Override
    public E inner(Vec<E, E> vector) throws OperationUndefinedException {
        return null;
    }

    @Override
    public Vec<E, E> copy() {
        return new Vector<>((Arrays.copyOf(data, data.length)));
    }

    @Override
    public E scalarInverse(E element) {
        try {
            return element.invert();
        } catch (NotInvertibleException e1) {
            return element.getZero();
        }
    }

    @Override
    public void pad(int num) {
        if (num > 0) {
            Object[] paddedData = new Object[data.length + num];
            for (int i = 0; i < data.length + num; i++) {
                if (i < data.length) {
                    paddedData[i] = data[i];
                } else {
                    paddedData[i] = data[0].getZero();
                }
            }
            this.data = Arrays.copyOf(paddedData, paddedData.length, (Class<? extends E[]>) data.getClass());
        }
    }

}
