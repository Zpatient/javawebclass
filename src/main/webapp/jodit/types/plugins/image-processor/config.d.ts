/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugins/image-processor
 */
declare module '../../config' {
    interface Config {
        imageProcessor: {
            replaceDataURIToBlobIdInView: boolean;
        };
    }
}
export {};