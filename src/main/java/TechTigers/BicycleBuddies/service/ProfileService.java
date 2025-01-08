//package TechTigers.BicycleBuddies.Controllers.service;
//
//import TechTigers.BicycleBuddies.data.ProfileRepository;
//import TechTigers.BicycleBuddies.models.Profile;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//TODO: work on UserService
//@Service
//public class ProfileService {
//    @Autowired
//    private final ProfileRepository profileRepository;
//    public ProfileService(ProfileRepository profileRepository){
//        this.profileRepository= profileRepository;
//    }
//  //VIEWS LIST OF PROFILES
//    public List<Profile> getAllProfiles(){
//        return (List<Profile>) profileRepository.findAll();
//    }
//
//    public Optional<Profile> getProfileById(int id){
    //  if(profileRepository.existsById(id)
//        return profileRepository.findById(id);
//    } else {
//          return "Profile" + id + "not found";
//       }
//      CREATE PROFILES
//    public Profile saveProfile(Profile profile){
//        return profileRepository.save(profile);
//    }
//
//    public void deleteProfile(int id){
//      if(!profileRepository.existsById(id)) {
//      throw new RuntimeException("Profile with ID" + id + "not found");
//      } else {
//        profileRepository.deleteById(id);
//    }
//      TODO: Work on CRUD functionality in controller & ask Eric about location SETTER
//      public Profile updateProfile(int id, Profile profileDetails){
//        Profile profile = profileRepository.findById(id).orElseThrow();
//        profile.setDisplayName(profileDetails.getDisplayName());
//        profile.setLocation(profileDetails.getLocation());
//        profile.setBioPicture(profileDetails.getBioPicture());
//        profile.setBio(profileDetails.getBio());
//        return profileRepository.save(profile);  // saves profile information
//    }
//}
