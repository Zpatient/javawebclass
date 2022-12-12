/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugins/print
 */
import type { IJodit, Nullable } from '../../../types';
/**
 * Generates a copy of an HTML document, resizes images, executes JS
 *
 * @event beforePreviewBox(string | undefined, 'pt' | 'px' | '')
 * @event afterPreviewBox(HTMLElement)
 */
export declare function previewBox(editor: IJodit, defaultValue?: string, points?: 'pt' | 'px' | '', container?: Nullable<HTMLElement>): HTMLElement;
