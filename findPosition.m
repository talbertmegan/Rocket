%finds position

function p = findPosition(velocity, oldPosition, dt)
    p = oldPosition + velocity*dt;
end