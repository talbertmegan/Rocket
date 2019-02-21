%%Handles acceleration



%Define constants
global GRAVITY Re Me
GRAVITY = 6.671E-11;
Re = 6.371E6;
Me = 5.972E24;

function a = findGravitationalAcceleration(position, mass)
    global GRAVITY Me
    radiusLength = sqrt( sum ( position.^2));
    radiusUnit = position./radiusLength;
    a = radiusUnit * (-GRAVITY * Me * mass / radiusLength^2);
    
end

function a = findTotalAcceleration(thrust, position, mass)
    a = thrust + findGravitationalAcceleration(position, mass)
end