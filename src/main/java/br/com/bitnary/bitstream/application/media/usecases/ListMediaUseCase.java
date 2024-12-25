package br.com.bitnary.bitstream.application.media.usecases;

import br.com.bitnary.bitstream.domain.media.Media;
import br.com.bitnary.bitstream.domain.media.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ListMediaUseCase {
    @Autowired
    MediaRepository mediaRepository;

    public List<Media> execute(String userId) {
        List<Media> list = mediaRepository.findByUserId(userId);

        return list;
    }
}
