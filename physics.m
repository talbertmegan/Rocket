% The beginning of the project
close all
clear all

    
%rocket properties
velocity = [0, 0, 0];
position = [0, 0, 0];
acceleration = [0, 0, 0]; %pre-init to reduce chance of errors
thrust = [0,100,0]; %rocket thrust


%n-body system properties, initial
% xposition, yposition, zposition, mass, xvelocity, yvelocity, zvelocity
system = [...
        [0,-6.371e6,0],5.972e24, [0,0,0];... %earth
        [0,383.4e6,0],7.3276e22, [-100,0,0]... %moo
    ];


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
figure
startTime = clock;
subplot(2,2,1)
title('Time v Position');
c = 0;
while(c<100)
    % TODO this can be optimized by combining linear approximations later
    %loop start time
    t1 = clock;
    time = etime(t1,startTime);
    %hefty calculations
    acceleration = findAcceleration(position,thrust,system);
    velocity = findVelocity(acceleration, velocity, finddt(t1));
    system = updateSystem(system, finddt(t1));

    %WE ARE NOT SURE HOW GRAPHING VECTORS WORKS
    % p(t) = p(t-dt) + dt*p'(t-dt) is linear approximation, valid for
    % sufficiently small dt. p'(t-dt) = velocityVector
    position = findPosition(velocity, position, finddt(t1));
    subplot(2,2,1)
    plot(c, position,'.');
%     xlim([0 10]);
%     ylim([0 0.1]);
    hold on
    subplot(2,2,2);
    plot(c, velocity,'.');
%     xlim([0 10]);
%     ylim([0 3]);
    hold on
    subplot(2,2,3);
    plot(c, acceleration, '.');
%     fprintf('%d is acceleration \n',acceleration);
%     fprintf('%d is velocity \n',velocity);
%     fprintf('%d is position \n', position);
    %assume time between t2 assignment and t1 assignment is negligble
    %break;
    drawnow;
    hold on
    c = c + 1;
end

function dt = finddt(t1)
    dt = etime(clock,t1);
end

