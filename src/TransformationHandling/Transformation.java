package TransformationHandling;

import ImageHandling.Image;

/**
 * Interface, който ще се имплементира от класовете за трансформации
 */
public interface Transformation {
    /**
     * Метод, който ще се имплементира от класовете за трансформации
     * @param image Параметър Image, чрез който ще се изпълняват трансформациите
     */
    void execute(Image image);
}
