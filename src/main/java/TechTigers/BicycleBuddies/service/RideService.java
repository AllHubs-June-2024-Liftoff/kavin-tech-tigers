package TechTigers.BicycleBuddies.service;

public class RideService {
    /**
     * private final RideRepository rideRepository;
     *     private final UserRepository userRepository;
     *
     *     @Autowired
     *     public RideService(RideRepository rideRepository) {
     *         this.rideRepository = rideRepository;
     *     }
     *
     *     public List<Ride> getAllRides(){
     *         return (List<Ride>) rideRepository.findAll();
     *     }
     *
     *     public Optional<Ride> getRideById(int id){
     *         if(!rideRepository.existsById(id)) {
     *             throw new RuntimeException("Ride with ID " +id+ " doesn't exist.");
     *         }
     *         return rideRepository.findById(id);
     *     }
     *
     *     public User saveRide(Ride ride) {
     *         return rideRepository.save(ride);
     *     }
     *
     *     public void deleteRide(int id) {
     *         if (!rideRepository.existsById(id)) {
     *         throw new RuntimeException("Ride with ID "+ id + "doesn't exist.");
     *         }
     *         rideRepository.deleteById(id);
     *     }
     *
     *     public User updateRide(int id, User updatedRide){
     *         ride existingRide = rideRepository.findById(id)
     *                 .orElseThrow(()-> new RuntimeException("Ride with ID "+ id +" does not exist."));
     *         existingRide.setDate(updatedRide.getDate());
     *         existingRide.setDistance(updatedRide.getDistance());
     *         existingRide.setDuration(updatedRide.getDuration());
     *         existingRide.setDescription(updatedRide.getDescription());
     *         return rideRepository.save(existingRide);
     *     }
     */
}
