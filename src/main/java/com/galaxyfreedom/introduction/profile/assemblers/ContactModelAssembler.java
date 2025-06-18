package com.galaxyfreedom.introduction.profile.assemblers;

import com.galaxyfreedom.introduction.profile.entity.Profile;
import com.galaxyfreedom.introduction.profile.mapper.ProfileMapper;
import com.galaxyfreedom.introduction.profile.model.ContactModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;

public class ContactModelAssembler extends RepresentationModelAssemblerSupport<Profile, ContactModel> {

    private static final Logger log = LoggerFactory.getLogger(ContactModelAssembler.class);

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public ContactModelAssembler(Class<?> controllerClass, Class<ContactModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected @NonNull ContactModel instantiateModel(@NonNull Profile entity) {
        return ProfileMapper.toContactModel(entity);
    }

    @Override
    public @NonNull ContactModel toModel(@NonNull Profile entity) {
        ContactModel contactModel = createModelWithId(entity.getId(), entity, entity.getId());
        log.info("ContactModel: {}", contactModel);
        return contactModel;
    }
}
