%%Handles acceleration
function a = acceleration(thrust, position)
    %Define constants
    global GRAVITY Re Me
    GRAVITY = 6.671E-11;
    Re = 6.371E6;
    Me = 5.972E24;
    a = thrust + findGravitationalAcceleration(position)
end






function a = findGravitationalAcceleration(position)
    global GRAVITY Me
    radiusLength = sqrt( sum ( position.^2));
    radiusUnit = position./radiusLength;
    a = radiusUnit * (-GRAVITY * Me / radiusLength^2);
    
end


