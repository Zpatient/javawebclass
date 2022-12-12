/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module modules/uploader
 */
import type { IUploader, IUploaderOptions } from '../../types';
declare module '../../config' {
    interface Config {
        /**
         * Enable drag and drop file editor
         */
        enableDragAndDropFileToEditor: boolean;
        uploader: IUploaderOptions<IUploader>;
    }
}