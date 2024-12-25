package br.com.bitnary.bitstream.application.media.usecases;

import br.com.bitnary.bitstream.application.auth.usecases.GetAuthenticatedUserUseCase;
import br.com.bitnary.bitstream.application.media.exceptions.MediaNotFoundException;
import br.com.bitnary.bitstream.domain.media.Media;
import br.com.bitnary.bitstream.domain.media.MediaRepository;
import br.com.bitnary.bitstream.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetMediaByIdUseCase {
    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;

    public Media execute(String mediaId) throws MediaNotFoundException {
        User user = getAuthenticatedUserUseCase.execute().orElseThrow();
        Media media = mediaRepository.findById(mediaId).orElseThrow(MediaNotFoundException::new);

        if (! user.getId().equals(media.getUser().getId())) {
            throw new MediaNotFoundException();
        }

        return media;
    }

}
