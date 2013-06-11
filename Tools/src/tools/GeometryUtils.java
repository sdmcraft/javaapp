package tools;

public class GeometryUtils
{
    public static boolean rectangleOverlap(int bottomLeftX1, int bottomLeftY1, int topLeftX1, int topLeftY1, int bottomRightX1, int bottomRightY1, int topRightX1, int topRightY1, int bottomLeftX2,
        int bottomLeftY2, int topLeftX2, int topLeftY2, int bottomRightX2, int bottomRightY2, int topRightX2, int topRightY2)
    {
        return !(((bottomRightX1 < bottomLeftX2) && (topRightX1 < topLeftX2)) || ((bottomLeftX1 > bottomRightX2) && (topLeftX1 > topRightX2)) ||
        ((topLeftY1 < bottomLeftY2) && (topRightY1 < bottomRightY1)) || ((bottomLeftY1 > topLeftY2) && (bottomRightY1 > topRightY2)));
    }

    public static void main(String[] args)
    {
        System.out.println(rectangleOverlap(0, 0, 0, 2, 2, 0, 2, 2, -1, -1, -1, 1, 1, -1, 1, 1));
        System.out.println(rectangleOverlap(3, 3, 3, 6, 6, 3, 6, 6, -1, -1, -1, 1, 1, -1, 1, 1));
    }
}
