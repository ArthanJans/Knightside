package me.arthan.knightside.utils

import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.sun.awt.SecurityWarning.setPosition




/** Determines whether the given rectangle and segment intersect
 * @param startX x-coordinate start of line segment
 * @param startY y-coordinate start of line segment
 * @param endX y-coordinate end of line segment
 * @param endY y-coordinate end of line segment
 * @param rectangle rectangle that is being tested for collision
 * @return whether the rectangle intersects with the line segment
 */
fun intersectSegmentRectangle(startX: Float, startY: Float, endX: Float, endY: Float, rectangle: Rectangle): Boolean {
    val rectangleEndX = rectangle.x + rectangle.width
    val rectangleEndY = rectangle.y + rectangle.height
    if (Intersector.intersectSegments(startX, startY, endX, endY, rectangle.x, rectangle.y, rectangle.x, rectangleEndY, null))
        return true
    if (Intersector.intersectSegments(startX, startY, endX, endY, rectangle.x, rectangle.y, rectangleEndX, rectangle.y, null))
        return true
    if (Intersector.intersectSegments(startX, startY, endX, endY, rectangleEndX, rectangle.y, rectangleEndX, rectangleEndY, null))
        return true
    return if (Intersector.intersectSegments(startX, startY, endX, endY, rectangle.x, rectangleEndY, rectangleEndX, rectangleEndY, null)) true else rectangle.contains(startX, startY)
}

/**
 * [.intersectSegmentRectangle]
 */
fun intersectSegmentRectangle(start: Vector2, end: Vector2, rectangle: Rectangle): Boolean {
    return intersectSegmentRectangle(start.x, start.y, end.x, end.y, rectangle)
}

fun intersectPolygonRectangle(p: Polygon, r: Rectangle): Boolean {
    val rPoly = Polygon(floatArrayOf(0f, 0f, r.width, 0f, r.width, r.height, 0f, r.height))
    rPoly.setPosition(r.x, r.y)
    return Intersector.intersectPolygons(rPoly, p, Polygon())
}