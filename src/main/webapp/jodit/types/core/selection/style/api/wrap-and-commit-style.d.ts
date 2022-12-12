/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
import type { IJodit } from '../../../../types';
import type { CommitStyle } from '../commit-style';
/**
 * Replaces the parent tag with the applicable one, or wraps the text and also replaces the tag
 * @private
 */
export declare function wrapAndCommitStyle(commitStyle: CommitStyle, font: HTMLElement, jodit: IJodit): HTMLElement;
