

function a = findAcceleration(position,thrust,system)
   g = findGravity(position,system);
   
   a = thrust + g;
   
end