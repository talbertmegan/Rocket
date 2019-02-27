% The beginning of the project

global GRAVITY
    GRAVITY = 6.671E-11;
    
%rocket properties
velocity = [0, 0, 0];
position = [0, 0, 0];
acceleration = [0, 0, 0]; %pre-init to reduce chance of errors
thrust = [0,0,0] %rocket thrust


%possible future additions (if someone else gets this project next year)
%airResistanceFactor = k
%angleOfAttack = theta
%liftFromAir = f(a,b,c)
%collisionboxes

% TODO add a wait for the game to begin otherwise the rocket will fall into earth at t=0    
% TODO add n-body system support

%General notes for the physics simulation
%startin position is set to be [0,0]
%all other calculations are based off this assumption
%so earth's starting position is [0,-6.371e6,0] (easier for n-body systems)


while(true)
    % TODO this can be optimized by combining linear approximations later
    %loop start time
    t1 = clock;
    
    %hefty calculations
    acceleration = findAcceleration(position,thrust);
    velocity = findVelocity(acceleration, velocity, finddt(t1));

    
    % p(t) = p(t-dt) + dt*p'(t-dt) is linear approximation, valid for
    % sufficiently small dt. p'(t-dt) = velocityVector
    position = findPosition(velocity, position, finddt(t1));
    %assume time between t2 assignment and t1 assignment is negligble

end

function dt = finddt(t1)
    dt = etime(clock,t1);
end

