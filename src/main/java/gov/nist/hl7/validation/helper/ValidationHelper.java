package gov.nist.hl7.validation.helper;

import gov.nist.validation.report.Report;
import hl7.v2.profile.Profile;
import hl7.v2.profile.XMLDeserializer;
import hl7.v2.validation.SyncHL7Validator;
import hl7.v2.validation.content.ConformanceContext;
import hl7.v2.validation.content.DefaultConformanceContext;
import hl7.v2.validation.vs.ValueSetLibrary;
import hl7.v2.validation.vs.ValueSetLibraryImpl;

import java.io.InputStream;
import java.util.List;

public class ValidationHelper {

    public static Report validate(InputStream profileXML, List<InputStream> constraintsXML, InputStream vsLibraryXML, String message, String id) throws Exception {
        Profile profile = getProfile(profileXML);
        ConformanceContext constraints = getConformanceContext(constraintsXML);
        ValueSetLibrary vsLibrary = getValueSetLibrary(vsLibraryXML);

        return new SyncHL7Validator(profile, vsLibrary, constraints).check(message, id);
    }

    static Profile getProfile(InputStream profile) {
        return XMLDeserializer.deserialize(profile).get();
    }

    static ConformanceContext getConformanceContext(List<InputStream> constraints) {
        return DefaultConformanceContext.apply(constraints).get();
    }


    static ValueSetLibrary getValueSetLibrary(InputStream vsLibrary) {
        return ValueSetLibraryImpl.apply(vsLibrary).get();
    }
}
