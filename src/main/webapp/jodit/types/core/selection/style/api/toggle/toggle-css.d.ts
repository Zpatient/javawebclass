/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
import type { CommitMode, IJodit } from '../../../../../types';
import type { CommitStyle } from '../../../../../core/selection/style/commit-style';
/**
 * Toggles css and classname
 * @private
 */
export declare function toggleCSS(commitStyle: CommitStyle, elm: HTMLElement, jodit: IJodit, mode: CommitMode, dry?: boolean): CommitMode;
