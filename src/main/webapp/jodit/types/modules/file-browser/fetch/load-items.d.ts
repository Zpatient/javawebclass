/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module modules/file-browser
 */
import type { IFileBrowser } from '../../../types';
/**
 * Loads a list of files and adds them to the state
 */
export declare function loadItems(fb: IFileBrowser): Promise<any>;