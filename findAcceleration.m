

function a = findAcceleration(position,thrust)
   g = findGravity(position);
   
   a = thrust + g;
   
end