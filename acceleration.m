


%%Handles acceleration
function a = FindAcceleration(thrust, position)

    a = thrust + findGravitationalAcceleration(position);
end

function a = findGravitationalAcceleration(positionOfObject)

    global GRAVITY Me
    radiusLength = sqrt( sum ( positionOfObject.^2));
    radiusUnit = positionOfObject./radiusLength;
    a = radiusUnit * (-GRAVITY * Me / radiusLength^2);
    
end





