package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f
    private val meterToPixels = 100f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        val xDisplacement = (velocityX * dT + (dT * dT / 6f) * (3 * accX + xAcc)) * meterToPixels
        val newVelocityX = velocityX + 0.5f * (xAcc + accX) * dT

        val yDisplacement = (velocityY * dT + (dT * dT / 6f) * (3 * accY + yAcc)) * meterToPixels
        val newVelocityY = velocityY + 0.5f * (yAcc + accY) * dT

        posX += xDisplacement
        posY += yDisplacement

        velocityX = newVelocityX
        velocityY = newVelocityY

        accX = xAcc
        accY = yAcc

        checkBoundaries()
    }

    fun checkBoundaries() {
        if (posX > backgroundWidth - ballSize) {
            posX = backgroundWidth - ballSize
            velocityX = 0f
            accX = 0f
        }
        if (posX < 0) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }
        if (posY > backgroundHeight - ballSize) {
            posY = backgroundHeight - ballSize
            velocityY = 0f
            accY = 0f
        }
        if (posY < 0) {
            posY = 0f
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        posX = (backgroundWidth - ballSize) / 2
        posY = (backgroundHeight - ballSize) / 2
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}