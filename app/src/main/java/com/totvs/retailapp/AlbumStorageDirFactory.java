package com.totvs.retailapp;

import java.io.File;

/**
 * Created by rond.borges on 01/12/2015.
 */
abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String albumName);
}