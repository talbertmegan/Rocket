
function v= findVelocity(acceleration, oldVelocity, dt)
    v = oldVelocity + dt*acceleration;
end